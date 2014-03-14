package com.eteam.frame.persistence;


import com.eteam.frame.domain.Sequence;

public interface SequenceMapper {
  Sequence getSequence(Sequence sequence);
  int updateSequence(Sequence sequence);
}
