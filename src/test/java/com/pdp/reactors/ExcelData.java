package com.pdp.reactors;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
class ExcelData{

    private String sheetName;
    private List<String> headers;
    private List<List<String>> contents;

}
