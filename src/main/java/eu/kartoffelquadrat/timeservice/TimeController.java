/**
 * OAuth2 secured version of the timeservice.
 * Following this tutorial: http://websystique.com/spring-security/secure-spring-rest-api-using-oauth2/
 *
 * @Author: Maximilian Schiedermeier
 * @Date: April 2019
 */
package eu.kartoffelquadrat.timeservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/***
 * This controller tells the time via a REST interface.
 */
@RestController
public class TimeController {

    private final TimeServiceUtils timeServiceUtils;

    public TimeController(@Autowired TimeServiceUtils timeServiceUtils) {
        this.timeServiceUtils = timeServiceUtils;
    }

    /**
     * Gateway method to access service functionality - may be replaced by other implementations, depending the feature selection.
     *
     * @return
     */
    @GetMapping("/time")
    public String getTime() {
        return timeServiceUtils.lookUpCurrentTime();
    }
}
