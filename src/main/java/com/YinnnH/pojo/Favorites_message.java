package com.YinnnH.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Favorites_message {
    private int id;
    private String favorites_name;
    private Integer message_id;
    private String message;
}
