package com.hellokoding.springboot.restful.poll;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.hellokoding.springboot.restful.participant.Participant;
import com.hellokoding.springboot.restful.slot.Slot;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity

@Data
public class Poll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String summary;
    
    @ManyToMany
    private List<Participant> participants;
    
    @OneToMany(mappedBy = "poll")
    private List<Slot> slots;
    
    public void addSlot(Slot slot) {
        if(!this.slots.contains(slot)) {
            this.slots.add(slot);
        }
    }
}
