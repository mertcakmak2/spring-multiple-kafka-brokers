package com.kafkamultiplebrokers.kafka;

import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Event {

    private String id;
    private String content;

}
