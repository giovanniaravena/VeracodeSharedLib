def scanArtifact(Map params){
  withCredentials([string(credentialsId: "${params.Id}", variable: 'SecretCredentialsVeracode')]) {
    // sh 'curl -O https://downloads.veracode.com/securityscan/pipeline-scan-LATEST.zip'
    // sh 'unzip pipeline-scan-LATEST.zip pipeline-scan.jar'
    sh "java -jar pipeline-scan.jar \
        --veracode_api_id ${params.Id} \
        --veracode_api_key ${SecretCredentialsVeracode} \
        --file ${params.file} \
        --fail_on_severity="Very High, High" \
        --fail_on_cwe="80" \
        --timeout "3" \
        --project_name ${params.ApplicationName} "
  }
}




