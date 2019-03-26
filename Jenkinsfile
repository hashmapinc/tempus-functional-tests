pipeline {
  agent {
    docker {
      image 'hashmapinc/tempusbuild:func-test-1'
      args '-u root -v /var/run/docker.sock:/var/run/docker.sock -v /dev/shm:/dev/shm'
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
