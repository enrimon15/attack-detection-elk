package com.datapipeline.kafkaelk.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class FilterDto {
    Map<String, Object> filters;
}
