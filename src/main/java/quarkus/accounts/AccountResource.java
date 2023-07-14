package quarkus.accounts;

import beans.Account;
import io.quarkus.runtime.util.StringUtil;
import jakarta.annotation.PostConstruct;
import jakarta.json.Json;
import jakarta.json.JsonObjectBuilder;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.*;

import static java.lang.String.*;

@Path("/accounts")
public class AccountResource {





    @PostConstruct
    public void setup() {

        accounts.add(new Account(123456789L, 987654321L, "George Baird", new BigDecimal("354.23")));
        accounts.add(new Account(121212121L, 888777666L, "Mary Taylor", new BigDecimal("560.03")));
        accounts.add(new Account(545454545L, 222444999L, "Diana Rigg", new BigDecimal("422.00")));
    }

    Set<Account> accounts = new HashSet<>();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Set<Account> allAccounts() {
        return accounts;
    }

    @GET
    @Path("/{accountNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public Account getAccount(@PathParam("accountNumber") Long accountNumber) {
        Optional<Account> response = accounts.stream()
                .filter(acct -> acct.getAccountNumber().equals(accountNumber))
                .findFirst();

        return response.orElseThrow(()
                -> new NotFoundException("Account with id of " + accountNumber + "does not exist."));
    }

    @GET
    @Path("/{inStr}")
    @Produces(MediaType.APPLICATION_JSON)
    public String strTransform(@PathParam("inStr") String inStr) {

        String outStr = "default";

        //String outStr = StringUtils.substring(inStr, 2, 5);
        //String outStr = inStr.substring(2,5);

        //String outStr = StringUtils.substring(inStr, -1);
        //String outStr = inStr.substring(inStr.length() - 1);

        String xxStr = "123456xx7899aBc";
        String xxStr1 = "123456xx7899aBc";
        String xxStr2 = "123456xx7899aBc7";


        //String outStr  = StringUtils.replace(xxStr, "xx", "y");
        //String outStr = xxStr.replace("xx","y");

        String kdnr = "5";
        String[] endings = {"1","3","5","7","9"};

        //if(StringUtils.endsWithAny(kdnr, "1","3","5","7","9")) { return "True";}
        //if(Arrays.stream(endings).anyMatch(kdnr::equals)){ return "True"; }

        //if(StringUtils.contains(xxStr,"xx7")) { return "True";}


        //if(String.contains(xxStr,"xx7")){ return "True";}
        //if(xxStr.contains("xx7")) { return "True";}

        //outStr = StringUtils.toRootUpperCase(xxStr);
        //outStr = xxStr.toUpperCase();

        //if(StringUtils.equals(xxStr,xxStr2)){ return "True";}
        //if(xxStr.equals(xxStr2)){ return "True";}

        //if(StringUtils.containsIgnoreCase("abcSeCu","sEcu")) { return "True";}
        //if("abcSeCu".toLowerCase().contains("sEcu".toLowerCase())) { return "True";}

        String dotStr = "abc.efghi";
        String basicStr = "abcBasic EFghi";

        //outStr = StringUtils.substringBefore(dotStr, ".");
        //outStr = "abcd.efg".substring(0, "abcd.efg".indexOf('.'));

        //outStr = StringUtils.substringAfter(dotStr, ".");
        //outStr = dotStr.substring(dotStr.indexOf('.')+1, dotStr.length());

        //outStr = StringUtils.substringAfter(basicStr, "Basic ");
        //outStr = basicStr.substring(basicStr.indexOf("Basic ")+"Basic ".length(), basicStr.length());

        String numStr = "578965";

        //if(StringUtils.startsWith(numStr, "5")) { return "True";}
        //if(numStr.startsWith("5")) { return "True";}

        //if(StringUtils.isNumeric(numStr)) { return "True";}
        // if(numStr.chars().allMatch( Character::isDigit )) { return "True";}

        String blankStr = "8";

        //if(  StringUtils.isBlank(blankStr)   ) { return "True";}
        //if(  blankStr.isBlank()  ) { return "True";}

        String blankStrA = "  f ";
        String blankStrB = " d ";

        //if(  StringUtils.isNoneBlank(blankStrA,blankStrB)  ) { return "True";}
        //if(  !blankStrA.isBlank() && !blankStrB.isBlank() ) { return "True";}

        //if(  StringUtils.equalsIgnoreCase("Abcdd", "aBcdd") ){ return "True";}
        //if("Abcdd".equalsIgnoreCase("aBcdd")) { return "True";}

        String anyStr = "abc_sMc_K_78";

        //if(  !StringUtils.containsAnyIgnoreCase(anyStr, "SMC_KT", "SMC_K") ){ return "True";}
        // if(  !anyStr.toUpperCase().contains("SMC_KT") && !anyStr.toUpperCase().contains("SMC_K") ){ return "True";}

        String idxStr = "abcdwinfghi";

        //Integer pos = StringUtils.indexOf(idxStr, "win");
        //Integer pos = idxStr.indexOf("win");
        //outStr = pos.toString();

        //outStr = StringUtils.join(idxStr,",");
        outStr = idxStr.concat(",");


        return outStr;
    }



    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createAccount(Account account) {
        if (account.getAccountNumber() == null) {
            throw new WebApplicationException("No Account number specified.", 400);
        }

        accounts.add(account);
        return Response.status(201).entity(account).build();
    }
}