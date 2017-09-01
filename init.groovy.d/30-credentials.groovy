import jenkins.model.*
import com.cloudbees.plugins.credentials.*
import com.cloudbees.plugins.credentials.common.*
import com.cloudbees.plugins.credentials.domains.*
import com.cloudbees.plugins.credentials.impl.*
import com.cloudbees.plugins.credentials.SystemCredentialsProvider
import com.cloudbees.jenkins.plugins.sshcredentials.impl.*
import org.jenkinsci.plugins.plaincredentials.*
import org.jenkinsci.plugins.plaincredentials.impl.*
import hudson.util.Secret
import hudson.plugins.sshslaves.*

domain = Domain.global()
store = SystemCredentialsProvider.getInstance().getStore()

priveteKey = new BasicSSHUserPrivateKey(
    CredentialsScope.GLOBAL,
    "jenkins-slave-key",
    "root",
    new BasicSSHUserPrivateKey.UsersPrivateKeySource(),
    "passphrase",
    "SSH private key"
)

usernameAndPassword = new UsernamePasswordCredentialsImpl(
    CredentialsScope.GLOBAL,
    "jenkins-slave-password",
    "Jenkis Slave with password configuration",
    "root",
    "jenkins"
)

gitLogin = new UsernamePasswordCredentialsImpl(
    CredentialsScope.GLOBAL,
    "git-login",
    "GitHub Login",
    "gituser",
    "gitpass"
)

def cmd = [
    'bash',
    '-c',
    '''echo 'secret-text-from-shell'
    '''
]
secretFromShell = cmd.execute().text
secretText = new StringCredentialsImpl(
    CredentialsScope.GLOBAL,
    secretFromShell, // id
    "Secret Text Description",
    Secret.fromString(secretFromShell)
)

store.addCredentials(domain, priveteKey)
store.addCredentials(domain, usernameAndPassword)
store.addCredentials(domain, gitLogin)
store.addCredentials(domain, secretText)
