def organisation = 'mobilelife'
def ACCESSTOKEN= // Github personal access token used here
def repoApi = new URL("https://api.github.com/orgs/${organisation}/repos?per_page=100&page=1&access_token=${ACCESSTOKEN}")
def repos = new groovy.json.JsonSlurper().parse(repoApi.newReader())
repoApi = new URL("https://api.github.com/orgs/${organisation}/repos?per_page=100&page=2&access_token=${ACCESSTOKEN}")
def repos2 = new groovy.json.JsonSlurper().parse(repoApi.newReader())
def reposFinal = repos+repos2

reposFinal.each {
    def repoName = it.name
    def gitUrl = it.git_url

    def jobName = "${repoName}".replaceAll('/','-')
    job(jobName) {
        scm {
            git(gitUrl,"master")
        }
        steps {
            maven("clean")
        }
    }
}

