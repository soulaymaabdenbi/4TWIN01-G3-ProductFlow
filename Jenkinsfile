pipeline {
    agent any
    tools {
        maven 'M2_HOME'
    }

    stages {
        stage('Git') {
            steps {
                echo 'Pulling'
                git branch: 'SoulaymaABDENBI-4TWIN1-G3', url: 'https://github.com/soulaymaabdenbi/4TWIN01-G3-ProductFlow.git'
            }
        }
        
        stage('Maven Clean Compile') {
            steps {
                dir('DevOps_Project') {
                    echo 'Running Maven Clean and Compile'
                    sh 'mvn clean compile'
                }
            }
        }
        
        stage('Maven Install') {
            steps {
                dir('DevOps_Project') {
                    echo 'Running Maven Install'
                    sh 'mvn install'
                }
            }
        }
        
        stage('Testing JUnit/Mockito') {
            steps {
                dir('DevOps_Project') {
                    script {
                        try {
                            sh 'mvn test'
                        } catch (Exception e) {
                            currentBuild.result = 'UNSTABLE'
                            throw e // Re-throw the exception to mark the build failed
                        }
                    }
                }
            }
        }
        stage('MVN SONARQUBE ') {
            steps {
                dir('DevOps_Project') {
                    
                  sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=sonar '
                     
                }
            }
        }
        stage('Deploy to Nexus') {
            steps {
                 dir('DevOps_Project') {
                script {
                    sh 'mvn deploy'
                }
                 }
            }
        }
         stage('Build Docker Image Backend') {
            steps {
                dir('DevOps_Project') {
                    sh 'docker build -t soulaymaabdenbi-4twin1-g3-productflow .'
                }
            }
        }
        
        stage('Push to Docker Hub Backend') {
            steps {
                dir('DevOps_Project') {
                    withCredentials([usernamePassword(credentialsId: 'dockerHub', passwordVariable: 'dockerHubPassword', usernameVariable: 'dockerHubUser')]) {
                        sh "docker login -u ${env.dockerHubUser} -p ${env.dockerHubPassword}"
                        sh 'docker tag soulaymaabdenbi-4twin1-g3-productflow souleymaabdenbi/soulaymaabdenbi-4twin1-g3-productflow:latest'
                        sh 'docker push souleymaabdenbi/soulaymaabdenbi-4twin1-g3-productflow:latest'
                    }
                }
            }
        }
         
         
        
        
         stage('Build Docker Image Frontend') {
            steps {
                dir('DevOps_Project_Front') {
                    // Build the Docker image using the tag you want for the frontend
                    sh 'docker build -t souleymaabdenbi/soulaymaabdenbi-4twin1-g3-frontend:latest .'
                }
            }
        }

        stage('Push to Docker Hub Frontend') {
            steps {
                // Make sure you're using the right directory where your Dockerfile is if it's different
                dir('DevOps_Project_Front') {
                    withCredentials([usernamePassword(credentialsId: 'dockerHub', passwordVariable: 'dockerHubPassword', usernameVariable: 'dockerHubUser')]) {
                        sh "docker login -u ${env.dockerHubUser} -p ${env.dockerHubPassword}"
                        // Tag and push the image to Docker Hub
                        sh 'docker push souleymaabdenbi/soulaymaabdenbi-4twin1-g3-frontend:latest'
                    }
                }
            }
        }

        stage('Docker Compose') {
            steps {
             script {
            sh'docker-compose pull'
            sh 'docker-compose down'
            sh 'docker-compose up -d'
            }
         }
        }

       
    }   
    
    
   post {
        success {
            mail to: 'cmptest66@gmail.com',
                 subject: "Build Successful: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                 body: "Good news, ${env.JOB_NAME} build ${env.BUILD_NUMBER} succeeded. Check it out!"
        }
        failure {
            mail to: 'cmptest66@gmail.com',
                 subject: "Build Failed: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                 body: "Something is broken with ${env.JOB_NAME} build ${env.BUILD_NUMBER}. Please check it!"
        }
    }
}
