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
                    // ✅ Run tests + generate JaCoCo report
                    bat 'mvn clean test jacoco:report'
                }

                dir('experience-service') {
                    bat 'mvn clean install'
                }

                dir('notification-service') {
                    bat 'mvn clean install'
                }
            }
        }

        stage('Coverage') {
            steps {
                dir('user-service') {
                    publishCoverage adapters: [
                        jacocoAdapter('target/site/jacoco/jacoco.xml')
                    ],
                    sourceFileResolver: sourceFiles('STORE_LAST_BUILD')
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