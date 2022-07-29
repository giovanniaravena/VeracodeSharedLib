@Library('VeracodeSharedLib') _
pipeline {
    agent any
    environment {
        currentDate = sh(returnStdout: true, script: 'date "+%d-%m-%C%y %H:%M:%S"').trim()
        artifactName = "artifact.zip"
        credentialID = "de4394251e60e821b5db8a0c63e311f5"
    }
    stages {

        stage('Check git') {
            steps {
                git credentialsId: 'f433e67d-629a-4e8c-9804-550f6322b13f', url: 'https://github.com/giovanniaravena/examplenode', branch: 'main'
            }
        }
        stage('Upload and Scan') {
            stages{
                stage('Artifact'){
                    steps {
                        zip archive: true, dir: '', exclude: '', glob: '', overwrite: true, zipFile: artifactName 
                    }
                }
                
                // stage('Upload Scan'){
                //     steps {
                //         echo currentDate
                //         script{
                //             policyScanLib.uploadArtifact(ApplicationName: "Desarrollo Seguro", UploadIncludesPattern: artifactName, Id: "${credentialID}",currentDate: "${currentDate}")
                //         }
                //     }
                // }
                
                stage('Veracode Pipeline Scan'){
                    steps {
                        script{
                            sh 'ls'
                            pipelineScanLib.scanArtifact(ApplicationName: "Desarrollo Seguro", file: artifactName, Id: "${credentialID}", currentDate: "${currentDate}")
                        }
                    }
                }
                
                stage('Veracode Pipeline Scan'){
                    steps {
                        script{
                            sh 'ls'
                            sca.scan(ApplicationName: "Desarrollo Seguro", file: artifactName, Id: "${credentialID}", currentDate: "${currentDate}")
                        }
                    }
                }
            }
        }
    }
} 