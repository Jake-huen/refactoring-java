package com.example.study.dto;

import com.example.study.domain.PlayType;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Play {
  @JsonSetter("playID")
  private String playId;

  @JsonSetter("name")
  private String name;

  @JsonSetter("type")
  private PlayType type;
}
