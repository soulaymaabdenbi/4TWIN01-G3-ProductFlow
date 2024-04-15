pipeline {
    agent any
    
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
        
        stage('MVN SONARQUBE ') {
            steps {
                dir('DevOps_Project') {
                    sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=nexus -Dmaven.test.skip=true'
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
         stage('Deploy to Nexus') {
            steps {
                 dir('DevOps_Project') {
                script {
                    sh 'mvn deploy'
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

                    
        
       
    }
    
    post {
        success {
            echo 'Build succeeded!'
        }
        failure {
            echo 'Build failed!'
        }
    }
}
