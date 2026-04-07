pipeline {
    agent any

    tools {
        maven 'Maven'
        jdk 'JDK17'
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'add-tests',
                    url: 'https://github.com/thejeswar2419/Placement-Syndicate-Devsecops.git'
            }
        }

        stage('Build & Test') {
            steps {
                bat 'mvn -v'

                dir('api-gatway') {
                    bat 'mvn clean install'
                }

                dir('discovery-service') {
                    bat 'mvn clean install'
                }

                dir('user-service') {
                    bat 'mvn clean test'
                }

                dir('experience-service') {
                    bat 'mvn clean install'
                }

                dir('notification-service') {
                    bat 'mvn clean install'
                }
            }
        }
    }

    post {
        always {
            junit '**/target/surefire-reports/*.xml'
        }
    }
}