def uploadArtifact(Map params){
  withCredentials([string(credentialsId: "${params.Id}", variable: 'SecretCredentialsVeracode')]) {
    veracode applicationName: "${params.ApplicationName}", canFailJob: false, debug: true, deleteIncompleteScan: true, 
    createSandbox: false, scanName: "${params.currentDate}", timeout: 1, uploadIncludesPattern: "${params.UploadIncludesPattern}", 
    vid: "${params.Id}", vkey: "${SecretCredentialsVeracode}", waitForScan: false
  }
}