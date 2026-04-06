pipeline {
    agent any

    environment {
        MAVEN_HOME = tool 'Maven'
        JAVA_HOME = tool 'JDK17'
        PATH = "${JAVA_HOME}/bin:${MAVEN_HOME}/bin:${env.PATH}"
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/YOUR_USERNAME/YOUR_REPO.git'
            }
        }

        stage('Build All Services') {
            steps {
                sh '''
                mvn -v

                cd api-gatway && mvn clean install
                cd ../discovery-service && mvn clean install
                cd ../user-service && mvn clean install
                cd ../experience-service && mvn clean install
                cd ../notification-service && mvn clean install
                '''
            }
        }

        stage('Run Tests') {
            steps {
                sh '''
                cd api-gatway && mvn test
                cd ../discovery-service && mvn test
                cd ../user-service && mvn test
                cd ../experience-service && mvn test
                cd ../notification-service && mvn test
                '''
            }
        }

        stage('Code Coverage') {
            steps {
                sh '''
                mvn jacoco:report
                '''
            }
        }

        stage('Security Scan (Optional)') {
            steps {
                echo "Add OWASP / Trivy here if needed"
            }
        }
    }

    post {
        always {
            junit '**/target/surefire-reports/*.xml'
        }

        success {
            echo 'Build Successful!'
        }

        failure {
            echo 'Build Failed!'
        }
    }
}