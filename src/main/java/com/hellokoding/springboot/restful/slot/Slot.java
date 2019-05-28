package com.hellokoding.springboot.restful.slot;

import lombok.Data;

import com.hellokoding.springboot.restful.poll.Poll;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import java.util.Date;

@Entity

@Data
public class Slot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date dateBegin;

    private Date dateEnd;
    
    @ManyToOne
    private Poll poll;
}
