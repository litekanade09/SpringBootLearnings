package JournalApp.MyFirstApp.Cache;


import JournalApp.MyFirstApp.Entry.ConfigAppEntity;
import JournalApp.MyFirstApp.Repository.ConfigAppRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {

    @Autowired
    private ConfigAppRepository configAppRepository;

    public Map<String,String>  app_cache = new HashMap<>();

    @PostConstruct
    public void init(){
        List<ConfigAppEntity> all = configAppRepository.findAll();
        System.out.println("✅ Fetched from DB: " + all);

        for(ConfigAppEntity configAppEntity : all){
            app_cache.put(configAppEntity.getKey(), configAppEntity.getValue());
        }

        System.out.println("✅ App Cache Loaded: " + app_cache);
    }



}
