/**
 * @Author: Maximilian Schiedermeier
 * @Date: April 2019
 */
package eu.kartoffelquadrat.timeservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Motivation of this class is to separate internal service functionality away from the REST controller.
 */
@Component
public class TimeServiceUtils {

    @Value("${date.format}")
    private String dateFormat;

    /**
     * Internal service functionality to generate a time string for the current time. Not directly exposed to others via
     * REST. If the injected date-format (defined in properties) is empty, this means the timeservice is in a dev
     * environment and will only return that it is "about teatime".
     *
     * @return
     */
    public String lookUpCurrentTime() {

        if (dateFormat.isEmpty())
            return "It's about teatime! (Hello from Dev)\n";

        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.format(new Date())+"\n";
    }
}
