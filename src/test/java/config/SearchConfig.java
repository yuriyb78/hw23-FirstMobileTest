package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:credentials/search.query"})
public interface SearchConfig extends Config{

    @Key("search.query")
    @DefaultValue("Appium")
    String searchQuery();


}
