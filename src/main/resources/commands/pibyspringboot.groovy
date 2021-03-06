package commands.crash

import org.crsh.cli.Command
import org.crsh.cli.Man
import org.crsh.cli.Usage
import org.crsh.command.InvocationContext
import org.springframework.beans.factory.ListableBeanFactory
import org.springframework.boot.actuate.endpoint.MetricsEndpoint
import org.springframework.boot.actuate.health.HealthIndicator

@Usage("Various command to interact with Piby-Spring-Boot")
class pibyspringboot{

    @Usage("Print out Piby-Spring-Boot  metrics")
    @Man("Iterate over all the metrics")
    @Command
    void metrics(InvocationContext context){
        ListableBeanFactory beanFactory = context.attributes['spring.beanfactory']
        beanFactory.getBeansOfType(MetricsEndpoint).each { name, metrics ->
            metrics.invoke().each {k, v ->
                out.println "${k} is at ${v}"
            }
        }
    }

    @Usage("Take a health check")
    @Man("Exercise all the health checks")
    @Command
    void health(InvocationContext context){
        ListableBeanFactory beanFactory = context.attributes['spring.beanfactory']
        beanFactory.getBeansOfType(HealthIndicator).each { name, healthIndicator ->
            def health = healthIndicator.health()
            out.println "${name} ... ${health.status} (${health.details})"
        }
    }
}