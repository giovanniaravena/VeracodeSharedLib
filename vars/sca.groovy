def scanProject(Map params){
  options {
      // only keep the last x build logs and artifacts (for space saving)
      buildDiscarder(logRotator(numToKeepStr: '20', artifactNumToKeepStr: '20'))
  }

  withCredentials([string(credentialsId: "${params.Id}", variable: 'SecretCredentialsVeracode')]) {
    sh '''
        touch SCA_Results_Build_${BUILD_NUMBER}.txt
        curl -sSL https://download.sourceclear.com/ci.sh | bash -s -- scan --update-advisor 2>&1 | tee SCA_Results_Build_${BUILD_NUMBER}.txt
        ! grep -E 'CVE-2021-45046|CVE-2021-22118' SCA_Results_Build_${BUILD_NUMBER}.txt
    '''
    
    // sh "cat filtered_results.json"
    // sh "cat results.json"
    // sh "cat DESSEG.json"

    sh "cat SCA_Results_Build_${BUILD_NUMBER}.txt"
  }
}
