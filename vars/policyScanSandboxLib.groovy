def uploadArtifact(Map params){
  withCredentials([string(credentialsId: 'b42d9a0dc0502a2c2ac0f19a8d9d8bf9', variable: 'SecretCredentialsVeracode')]) {
    veracode applicationName: "${params.ApplicationName}", canFailJob: false, debug: true, deleteIncompleteScan: true, fileNamePattern: '', replacementPattern: '', createSandbox: false, sandboxName: '', scanExcludesPattern: '', scanIncludesPattern: '' ,scanName: "${params.currentDate}", teams: '', timeout: 1, uploadIncludesPattern: "${params.UploadIncludesPattern}", vid: "b42d9a0dc0502a2c2ac0f19a8d9d8bf9", vkey: "${SecretCredentialsVeracode}", waitForScan: false
  }
}