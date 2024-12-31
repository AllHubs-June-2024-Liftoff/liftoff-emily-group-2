package com.notsauce.parkd.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class User extends AbstractEntity {


}

// For Vincent mainly:
// Would like a # of logins counter/data point
// Also a last logged in tracker (though granularity only necessary to 1-day intervals)