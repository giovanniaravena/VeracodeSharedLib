def uploadArtifact(Map params){
  withCredentials([string(credentialsId: "${params.Id}", variable: 'SecretCredentialsVeracode')]) {
    veracode applicationName: "${params.ApplicationName}", canFailJob: true, debug: true, deleteIncompleteScan: true, 
    createSandbox: false, scanName: "${params.currentDate}", timeout: 30, uploadIncludesPattern: "${params.UploadIncludesPattern}", 
    vid: "${params.Id}", vkey: "${SecretCredentialsVeracode}", waitForScan: true
  }
}