package com.arquitectura.data_rmi_initial_project;

import jakarta.ejb.Remote;

import java.util.List;

@Remote
public interface ExampleService {
    String fetchData();
    List<String> fetchDatabaseData();
}
