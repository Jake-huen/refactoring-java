package com.example.study.dto;

import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Performance {
    @JsonSetter("playID")
    private String playId;

    @JsonSetter("audience")
    private int audience;
}
