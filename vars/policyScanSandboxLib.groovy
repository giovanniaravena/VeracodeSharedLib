def uploadArtifact(Map params){
  withCredentials([string(credentialsId: "${params.Id}", variable: 'SecretCredentialsVeracode')]) {
    veracode applicationName: "${params.ApplicationName}", canFailJob: false, debug: true, deleteIncompleteScan: true, 
    fileNamePattern: '', replacementPattern: '', createSandbox: true, sandboxName: "${params.sandboxName}", scanExcludesPattern: '', 
    scanIncludesPattern: '' ,scanName: "${params.currentDate}", teams: '', timeout: 1, 
    uploadIncludesPattern: "${params.UploadIncludesPattern}", vid: "${params.Id}", vkey: "${SecretCredentialsVeracode}", 
    waitForScan: false
  }
}