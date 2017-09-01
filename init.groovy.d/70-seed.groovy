import com.cloudbees.jenkins.plugins.sshcredentials.impl.*
import com.cloudbees.plugins.credentials.*
import com.cloudbees.plugins.credentials.common.*
import com.cloudbees.plugins.credentials.domains.*
import hudson.model.*
import hudson.plugins.git.*
import hudson.plugins.sshslaves.*
import hudson.security.ACL
import hudson.slaves.*
import hudson.triggers.TimerTrigger
import java.util.Collections
import java.util.List
import javaposse.jobdsl.plugin.*
import jenkins.model.*

def jobName = 'seed'

// if (Jenkins.instance.getItem(jobName)) {
//     println 'INFO: Initial Seed job already exists, skipping'
//     return
// }

println "Setting up seed job"
def project = new FreeStyleProject(Jenkins.instance, jobName)

// TODO uncomment, once the repo and credentials are setup and working
//
// List<BranchSpec> branches = Collections.singletonList(new BranchSpec('*/master'))
// List<UserRemoteConfig> repos = Collections.singletonList(
//     new UserRemoteConfig('https://github.com/mobilelife/mobilelife-jenkins.git',
//         '',
//         '',
//         'TODO-credentials-id-go-here-1111'))
// GitSCM scm = new GitSCM(repos,
//     branches,
//     false,
//     null, null, null, null);
// project.setScm(scm)
String dslScript = new File('/var/jenkins_home/init.groovy.d/jobs/job-promotion-example.groovy.dsl').getText('UTF-8')

// TODO adjust seed jobs according to https://github.com/jenkinsci/job-dsl-plugin/wiki/Migration#migrating-to-149
def jobDslBuildStep = new ExecuteDslScripts()
jobDslBuildStep.setScriptText(dslScript)

//jobDslBuildStep.setUseScriptText(false)
//jobDslBuildStep.setTargets('jobs/*.groovy')

// TODO auto-approve scripts - https://github.com/jenkinsci/job-dsl-plugin/wiki/Script-Security

project.getBuildersList().add(jobDslBuildStep)
project.save()
Jenkins.instance.reload()

//schedule the Queue job with a quiet period of 2 mins
def job = hudson.model.Hudson.instance.getJob(jobName)
Hudson.instance.queue.schedule(job,2,null,null)

//if (!jenkins.model.Jenkins.instance.getItemByFullName(jobName)) {
//    queue(jobName)
//}

