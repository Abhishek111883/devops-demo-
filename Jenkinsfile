pipeline{
    agent any

    environment {
        //Git and Docker configuration
        GIT_URL = 'https://github.com/Abhishek111883/devops-demo-.git'
        GIT_BRANCH = '*/main'
        DOCKER_IMAGE = 'developer597/publish_using_jenkins'
        DOCKER_TAG = 'latest'
        DOCKER_CREDENTIALS = credentials('2d0bf101-09fb-4946-9c3d-9baba7f0d980')
    }
    parameters {
            string(name: 'DEPLOY_ENV', defaultValue: 'dev', description: 'Deployment Environment')
            booleanParam(name: 'RUN_TESTS', defaultValue: true, description: 'Run Tests?')
    }

    stages {
        stage('CheckOut') {
            steps {
                script {
                    checkout([
                        $class: 'GitSCM',
                        branches: [[name: "${GIT_BRANCH}"]],
                        userRemoteConfigs: [[url: "${GIT_URL}"]],
                        extensions: [
                            [$class: 'CleanBeforeCheckout'],
                            [$class: 'CloneOption', noTags: false, shallow: true, depth: 1]
                        ]
                    ])
                }
            }
        }

        stage('Build'){
            steps{
                script {
                    sh '''
                    chmod +x gradlew
                    ./gradlew build

                    '''
                }
            }
        }

        stage('Test'){
            when {
                expression { params.RUN_TESTS }
            }
            steps{
                script {
                    sh '''
                    chmod +x gradlew
                    ./gradlew test
                    '''
                }
            }
        }

        stage('Publish Test Results') {
            steps {
                junit '**/build/test-results/test/*.xml',
            }
        }

        stage('Archive Artifacts') {
            steps {
                archiveArtifacts artifacts: 'build/libs/*.jar', fingerprint: true
            }
        }

        stage('Docker Build & Push') {
            steps {
                script {
                    sh '''
                    docker build -t ${DOCKER_IMAGE}:${DOCKER_TAG} .
                    echo "${DOCKER_CREDENTIALS_PSW}" | docker login -u "${DOCKER_CREDENTIALS_USR}" --password-stdin
                    docker push ${DOCKER_IMAGE}:${DOCKER_TAG}
                    '''
                }
            }
        }

        stage('Approve Deployment') {
            steps {
                input message: 'Approve Deployment?', ok: 'Deploy Now'
            }
        }

        stage('Deploy') {
            steps {
                script {
                    sh '''
                    echo "Deploying application... to ${params.DEPLOY_ENV} environment"
                    '''
                }
            }
        }
    }

    post{
        success{
            echo "====++++only when successful++++===="
        }
        failure{
            echo "====++++only when failed++++===="
        }
        always{
            echo '📦 Cleaning up workspace...'
            cleanWs()
        }
    }
}


