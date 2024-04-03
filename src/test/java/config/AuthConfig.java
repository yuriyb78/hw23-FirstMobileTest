package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:credentials/user.auth"})
public interface AuthConfig extends Config{

    String userName();
    String password();
    String testNameApp();
    String deviceModel();
    String osVersion();


}
