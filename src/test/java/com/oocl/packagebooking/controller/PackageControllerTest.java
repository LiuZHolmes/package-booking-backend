package com.oocl.packagebooking.controller;

import com.oocl.packagebooking.Entity.Package;
import com.oocl.packagebooking.Repository.PackageRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PackageControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private PackageRepository packageRepository;

    private List<Package> packageList;

    @Before
    public void setUp() throws Exception {
        packageList = new ArrayList<>();
        packageList.add(new Package(1L,"张三", "123456", "no_appointment", new Date()));
        packageList.add(new Package(2L,"李四", "789456", "already_appointment", new Date()));
        packageList.add(new Package(3L,"王五", "963258", "taken", new Date()));
    }

    @Test
    public void should_return_packages_when_get_it() throws Exception {
        // given
        when(packageRepository.findAll()).thenReturn(packageList);
        // when
        mockMvc.perform(get("/packages"))
                // then
                .andExpect(jsonPath("$.length()")
                        .value(3));
    }

    @Test
    public void should_return_filtered_packages_when_get_it() throws Exception {
        // given
        when(packageRepository.findAll()).thenReturn(packageList);
        // when
        mockMvc.perform(get("/packages")
                .param("status", "already_appointment"))
                // then
                .andExpect(jsonPath("$.length()")
                        .value(1))
                .andExpect(jsonPath("$[0].status")
                        .value("already_appointment"));
    }

    @Test
    public void should_return_a_package_when_put_it_by_id_on_status() throws Exception {
        // given
        Package aPackage = packageList.get(0);
        when(packageRepository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(aPackage));
        aPackage.setStatus("taken");
        when(packageRepository.save(any(Package.class))).thenReturn(aPackage);
        // when
        mockMvc.perform(put("/packages/" + aPackage.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"status\": \"taken\"\n" +
                        "}"))
                // then
                .andExpect(jsonPath("$.status")
                        .value("taken"));
    }
}
