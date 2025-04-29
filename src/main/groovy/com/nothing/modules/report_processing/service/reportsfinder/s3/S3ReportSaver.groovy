package com.nothing.modules.report_processing.service.reportsfinder.s3

import com.fasterxml.jackson.databind.ObjectMapper
import com.nothing.helper.annotations.springcomponents.InjectableService
import com.nothing.modules.report_processing.data.Metadata
import com.nothing.modules.report_processing.data.Similarity
import com.nothing.modules.report_processing.service.reportsfinder.interfaces.ReportSaver
import org.springframework.beans.factory.annotation.Value
import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider
import software.amazon.awssdk.services.s3.S3Client

@InjectableService class S3ReportSaver implements ReportSaver {
    def s3Client = S3Client
            .builder()
            .credentialsProvider(EnvironmentVariableCredentialsProvider.create())
            .build()

    @Value("${aws.bucketName}") String bucketName
    private static final def jsonMapper = new ObjectMapper()

    @Override void save(String key, List<List<Map<String, Tuple2<Metadata, List<Similarity>>>>> clusters) {
        def file = new File(key)
        def toWrite = [data: clusters]
        file.createNewFile()
        file.write(jsonMapper.writeValueAsString(toWrite))

        s3Client.putObject(request -> request
                .bucket(bucketName)
                .key(file.getName())
                .ifNoneMatch("*"), file.toPath())
    }
}
