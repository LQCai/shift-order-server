//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.springblade.core.oss.config;

import com.qiniu.common.Zone;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.springblade.core.oss.QiniuTemplate;
import org.springblade.core.oss.props.OssProperties;
import org.springblade.core.oss.rule.BladeOssRule;
import org.springblade.core.oss.rule.OssRule;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(
        proxyBeanMethods = false
)
@EnableConfigurationProperties({OssProperties.class})
@ConditionalOnProperty(
        value = {"oss.name"},
        havingValue = "qiniu"
)
public class QiniuConfiguration {
    private OssProperties ossProperties;

    @Bean
    @ConditionalOnMissingBean({OssRule.class})
    public OssRule ossRule() {
        return new BladeOssRule();
    }

    @Bean(name = "qiniuConfigurationFunc")
    public com.qiniu.storage.Configuration qiniuConfiguration() {
        return new com.qiniu.storage.Configuration(Zone.autoZone());
    }

    @Bean
    public Auth auth() {
        return Auth.create(this.ossProperties.getAccessKey(), this.ossProperties.getSecretKey());
    }

    @Bean
    @ConditionalOnBean({com.qiniu.storage.Configuration.class})
    public UploadManager uploadManager(com.qiniu.storage.Configuration cfg) {
        return new UploadManager(cfg);
    }

    @Bean
    @ConditionalOnBean({com.qiniu.storage.Configuration.class})
    public BucketManager bucketManager(com.qiniu.storage.Configuration cfg) {
        return new BucketManager(Auth.create(this.ossProperties.getAccessKey(), this.ossProperties.getSecretKey()), cfg);
    }

    @Bean
    @ConditionalOnMissingBean({QiniuTemplate.class})
    @ConditionalOnBean({Auth.class, UploadManager.class, BucketManager.class, OssRule.class})
    public QiniuTemplate qiniuTemplate(Auth auth, UploadManager uploadManager, BucketManager bucketManager, OssRule ossRule) {
        return new QiniuTemplate(auth, uploadManager, bucketManager, this.ossProperties, ossRule);
    }

    public QiniuConfiguration(final OssProperties ossProperties) {
        this.ossProperties = ossProperties;
    }
}
