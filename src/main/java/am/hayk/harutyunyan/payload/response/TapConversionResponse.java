package am.hayk.harutyunyan.payload.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class TapConversionResponse {
    public int id;
    public String external_id;
    public int amount;
    public String click;
    public ArrayList<Commission> commissions;
    public Program program;
    public Affiliate affiliate;
    public Date created_at;
    public Object warnings;
}

@Getter
@Setter
@NoArgsConstructor
class Affiliate{
    public String id;
    public String firstname;
    public String lastname;
}

@Getter
@Setter
@NoArgsConstructor
class Commission{
    public int id;
    public int conversion_sub_amount;
    public int amount;
    public String commission_type;
    public boolean approved;
    public Affiliate affiliate;
    public String kind;
    public String currency;
    public Date created_at;
}

@Getter
@Setter
@NoArgsConstructor
class Program{
    public String id;
    public String title;
    public String currency;
}


