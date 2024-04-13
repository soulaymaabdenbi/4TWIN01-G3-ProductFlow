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
        
        stage('Build Docker Image') {
            steps {
                dir('DevOps_Project') {
                // Build the Docker image using the tag you want
                sh 'docker build -t soulaymaabdenbi-4twin1-g3-productflow .'
            }}
        }

        stage('Push to Docker Hub') {
            steps {
                dir('DevOps_Project') {
                    withCredentials([usernamePassword(credentialsId: 'dockerHub', passwordVariable: 'dockerHubPassword', usernameVariable: 'dockerHubUser')]) {
          sh "docker login -u ${env.dockerHubUser} -p ${env.dockerHubPassword}"
                        // Tag and push the image to Docker Hub
                        sh 'docker tag soulaymaabdenbi-4twin1-g3-productflow souleymaabdenbi/soulaymaabdenbi-4twin1-g3-productflow:latest'
                        sh 'docker push souleymaabdenbi/soulaymaabdenbi-4twin1-g3-productflow:latest'
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
