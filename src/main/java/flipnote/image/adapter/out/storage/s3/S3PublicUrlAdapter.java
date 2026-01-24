package flipnote.image.adapter.out.storage.s3;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import flipnote.image.application.port.out.PublicUrlPort;

@Component
public class S3PublicUrlAdapter implements PublicUrlPort {

	@Value("${cloud.s3.bucket}")
	private String bucket;

	@Value("${cloud.s3.region}")
	private String region;

	/**
	 * S3 키로부터 url 추출
	 * @param s3Key
	 * @return
	 */
	@Override
	public String urlOf(String s3Key) {
		return "https://"+bucket+".s3."+region+".awazonaws.com/"+s3Key;
	}
}
