pipeline {
  agent {
    docker {
      image 'selenium/standalone-chrome'
      args '-u root -v /var/run/docker.sock:/var/run/docker.sock'
    }
  }
  stages{
    stage('Initialize') {
        steps {     
          sh '''mvn clean
                mvn validate'''
          }
      }
      stage('Build') {
        steps {
          sh 'mvn -Dmaven.test.failure.ignore=true install'
        }
    }
      stage('Test') {
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
