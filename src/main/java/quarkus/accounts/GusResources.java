package quarkus.accounts;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.ext.Provider;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;


import static java.lang.String.*;

@Path("/gus")
public class GusResources {

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> strTransform(@PathParam("inStr") String inStr) {

        List<String> strList = List.of(" abc,hij    "," def,dfg","ghi,jkl ");

        //List<String> outList = strList.stream().map(t -> List.of(StringUtils.split(t, ","))).flatMap(Collection::stream).map(StringUtils::trim).collect(Collectors.toList());

        // List<String> outList = strList.stream().map(t -> List.of( t.split(",") )  ).flatMap(Collection::stream).map(x -> x.trim()).collect(Collectors.toList());

        //List<String> outList  = StringUtils.join(strList.stream().collect(Collectors.toList() ),",");


        return null;
    }

    @GET
    @Path("/concat")
    @Produces(MediaType.APPLICATION_JSON)
    public String strConcat() {

        String[] strList = {"hello", "hello", "edpresso"};
        //List<String> strList = ["hello", "educative", "edpresso"];

        List<String> strList2 = new ArrayList<>();

        strList2.add("abc");
        strList2.add("abc");
        strList2.add("efg");
        strList2.add("hij");
        strList2.add("hij");

        char sep = '-';

        //String outStr = StringUtils.join(strList2.stream().distinct().collect(Collectors.toList()),",");
        String outStr = strList2.stream().distinct().collect(Collectors.joining(","));

        //String outStr = StringUtils.join(strList, sep);
        //String outStr = strList.stream().collect(Collectors.joining(","));

        return outStr;


    }

    @GET
    @Path("/obfu")
    @Produces(MediaType.APPLICATION_JSON)
    public String strObfuscation(@PathParam("inStr") String inStr) {

     String outStr = "";

        List<String> obfuscatedChars = new ArrayList<>();
        obfuscatedChars.add("abc");
        obfuscatedChars.add("efg");

        outStr = StringUtils.join(obfuscatedChars,",");
        //outStr = obfuscatedChars.stream().collect(Collectors.joining(","));

/*        outStr = obfuscatedChars.stream()
       .map(n -> String.valueOf(n))
       .collect(Collectors.joining(","));*/

        return outStr;
    }

    @GET
    @Path("/date")
    @Produces(MediaType.APPLICATION_JSON)
    public String dateTransform(@PathParam("inStr") String inStr) throws ParseException {

        FastDateFormat format = FastDateFormat.getInstance("dd.MM.yyyy HH:mm:ss");


        String dateStr1 = "2014-04-27T13:45:31";
        String dateStr2 = "27.04.2014 13:45:31.123";

        Date expiration = FastDateFormat.getInstance("yyyy-MM-dd'T'HH:mm:ss").parse( dateStr1 );

        LocalDateTime dateTime = LocalDateTime.parse(dateStr1);

        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss.SSS");
        String getTime = String.valueOf(  FastDateFormat.getInstance("dd.MM.yyyy HH:mm:ss.SSS").parse(dateStr2).getTime()  );

        LocalDateTime dateTime2 = LocalDateTime.parse(dateStr2, formatter2);


        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        String formattedDateTime = dateTime.format(dateTimeFormatter);
        String formattedDateTime2 = dateTime2.format(dateTimeFormatter);


        String outStr = format.format(expiration);
        String outStr2 = formattedDateTime;

        return outStr2;
        //return formattedDateTime;


    }

    @GET
    @Path("/gettime")
    @Produces(MediaType.APPLICATION_JSON)
    public long getTime(@PathParam("inStr") String inStr) throws ParseException {

        String dateStr2 = "27.04.2014 13:45:31.123";
        //String getTime = String.valueOf(  FastDateFormat.getInstance("dd.MM.yyyy HH:mm:ss.SSS").parse(dateStr2).getTime()  );

        long getTime = FastDateFormat.getInstance("dd.MM.yyyy HH:mm:ss.SSS").parse(dateStr2).getTime();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss.SSS");



        long getTime2 = Timestamp.valueOf(LocalDateTime.parse(dateStr2,formatter)).getTime();

        return getTime2;
    }

    @GET
    @Path("/format")
    @Produces(MediaType.APPLICATION_JSON)
    public String format(@PathParam("inStr") String inStr) throws ParseException {

        String dateStr2 = "27.04.2014 13:45:31.123";
        //String getTime = String.valueOf(  FastDateFormat.getInstance("dd.MM.yyyy HH:mm:ss.SSS").parse(dateStr2).getTime()  );

        String date1 = FastDateFormat.getInstance("yyyy-MM-dd'T'HH:mm:ss").format(new Date());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        String date2 = dateFormat.format(new Date());

        //2023-07-14T11:06:12
        //2023-07-14T11:06:40



        return date1;
    }
}


