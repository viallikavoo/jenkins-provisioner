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

def jobNames = [
	'job1',
	'job2',
	'job3',
	'job4'
]
def branches = [
	'master',
	'v1',
	'v2',
	'v3'
]

nestedView('main-view') {
	branches.each { branchName ->
		views {
			nestedView('jobs-for-' + branchName) {
				views {
					listView(branchName + '-jobs') {
						jobs {
							jobNames.each { jobName ->
								name(jobName)
							}
						}
						columns {
							status()
							weather()
							name()
							lastSuccess()
							lastFailure()
							lastDuration()
							buildButton()
						}
					}
				}
			}
		}
    }
}
