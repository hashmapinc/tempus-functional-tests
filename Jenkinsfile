pipeline {
  agent {
    docker {
      image 'hashmapinc/tempusbuild:func-test'
      args '-u root -v /var/run/docker.sock:/var/run/docker.sock --shm-size=8g'
    }
  }
  stages{
    stage('Initialize') {
        steps {     
          sh '''mvn clean
                mvn validate'''
          }
      }
      stage('Test ') {
        steps {
          sh 'mvn test'
        }
      } 
      stage('Report and Archive') {
        steps {
          archiveArtifacts '**/target/*.jar'
        }
      } 
  }          
  post {
    always {
      sh 'chmod -R 777 .'
        }
    }
}
