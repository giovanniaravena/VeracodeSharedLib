def scanArtifact(Map params){
  withCredentials([string(credentialsId: "${params.Id}", variable: 'SecretCredentialsVeracode')]) {
    // sh 'curl -O https://downloads.veracode.com/securityscan/pipeline-scan-LATEST.zip'
    // sh 'unzip pipeline-scan-LATEST.zip pipeline-scan.jar'
    sh "ls"

    sh "java -jar pipeline-scan.jar \
        --veracode_api_id ${params.Id} \
        --veracode_api_key ${SecretCredentialsVeracode} \
        --request_policy 'DESSEG' "

    sh "ls"

    sh "java -jar pipeline-scan.jar \
        --veracode_api_id ${params.Id} \
        --veracode_api_key ${SecretCredentialsVeracode} \
        --file ${params.file} \
        --policy_file 'DESSEG.json' \
        --timeout '3' \
        --verbose 'true' \
        --project_name '${params.ApplicationName}' "
        // --fail_on_severity='Very High, High, Medium' \
        // --fail_on_cwe='80' \
    sh "ls"
  }
}




