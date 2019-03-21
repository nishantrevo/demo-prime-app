package com.nishantrevo.demoapi.test.unit;

import com.nishantrevo.demoapi.service.InMemoryCache;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

public class InMemoryCacheTests {

    private InMemoryCache<String, String> cache;

    @BeforeMethod
    public void setupCache(){
        cache = new InMemoryCache<>();
    }
    @AfterMethod
    public void cleanUpCache(){
        cache = null;
    }

    @Test(description = "isCached() returns false when key is not present")
    public void isCached_returns_false_when_key_not_present(){
        String key = "k1";
        assertThat(cache.isCached(key))
                .as("Find " + key + "before adding")
                .isFalse();
    }

    @Test(description = "isCached() returns true when key is present")
    public void isCached_returns_true_when_key_is_present(){
        String key = "k1";
        String value = "val";
        cache.addToCache(key, value);
        assertThat(cache.isCached(key))
                .as("Find " + key + "after adding")
                .isTrue();
    }

    @Test(description = "getCachedValue() returns null when key is not present")
    public void getCachedValue_returns_null_when_key_not_present(){
        String key = "1";
        assertThat(cache.getCachedValue(key))
                .as("Get " + key + "before adding")
                .isNull();
    }

    @Test(description = "getCachedValue() returns value when key is present")
    public void getCachedValue_returns_value_when_key_is_present(){
        String key = "k1";
        String value = new String ("v1");
        cache.addToCache(key, value);
        assertThat(cache.getCachedValue(key))
                .as("Get " + key + "after adding")
                .isNotNull()
                .isEqualTo(value);
    }

    @Test(description = "Key cannot be null. Throws NullPointerException."
            , expectedExceptions = NullPointerException.class
    )
    public void key_cannot_be_null() {
        cache.addToCache(null, "nullValue");
    }

    @Test(description = "Value cannot be null. Throws NullPointerException."
            , expectedExceptions = NullPointerException.class
    )
    public void value_cannot_be_null() {
        cache.addToCache("k123", null);
    }

    @Test(description = "addToCache() adds key-value to cache")
    public void add_new_key_value(){
        String key = "key1";
        String value = "value1";

        cache.addToCache(key, value);

        assertThat(cache.isCached(key))
                .as("Find " + key + "after adding")
                .isEqualTo(true);
        assertThat(cache.getCachedValue(key))
                .as("Find " + key + "after adding")
                .isNotNull()
                .isEqualTo(value);
    }

    @Test(description = "addToCahche() replaces value if key already present.")
    public void addToCache_replaces_previous_value_for_duplicate_key(){
        String key = "key1";
        String value1 = new String("value1");
        String value2 = new String("value2");

        String replacedValue = cache.addToCache(key, value1);
        assertThat(replacedValue)
                .as("nothing is replaced for first time")
                .isNull();

        assertThat(cache.isCached(key))
                .as("isCached " + key + "after adding")
                .isEqualTo(true);
        assertThat(cache.getCachedValue(key))
                .as("isCached " + key + "after adding")
                .isNotNull()
                .isEqualTo(value1);

        String replacedValueDuplicateKey = cache.addToCache(key, value2);
        assertThat(replacedValueDuplicateKey)
                .as("Replaced value")
                .isNotNull()
                .isEqualTo(value1)
                .isNotEqualTo(value2);
    }

}
