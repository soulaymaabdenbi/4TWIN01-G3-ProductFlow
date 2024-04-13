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
