Connected Industries Open Framework - Hyper Dictionary Server
====

Connected Industries Open Framework - Hyper Dictionary Server (CIOF-HDS) is the RESTful API server to manage the data dictionaries that are used by manufacturing platforms. 

## Modules

The project includes multi modules as maven project.

* `ciof-hds-dev`: Java web application for HDS.
* `ciof-hds-ops`: Ansible playbook to install HDS.

## Building

Building CIOF-HDS from source, you will need:

* JDK 8 (or newer)
* Maven

To build CIOF-HDS:

    mvn clean package

It makes the gzip file that includes Ansible playbook for installation of CIOF-HDS.
The gzip file is generated in `ciof-hds-ops/target/hds-playbook.tar.gz`.

## Installation

The `hds-playbook.tar.gz` includes playbook that are to be run on the following operating systems:

* Cent OS 7
* Red Hat Enterprise Linux 7

You need to [install Ansible](https://docs.ansible.com/ansible/latest/installation_guide/intro_installation.html) before running the playbook.

To install CIOF-HDS, unarchive the `hds-playbook.tar.gz`, and run playbook inside the unarchived directory:

    # tar xvf hds-playbook.tar.gz
    # cd hds-playbook
    # ansible-playbook -c local -i localhost, site.yml

After finishing to run playbook, CIOF-HDS will start on the system. After installation, there is no user for the CIOF-HDS.

To add administrator user:

    # /opt/hds/bin/adduser.sh <username> <password> ADMINISTRATOR

This will create a new user. You need to enter your new user name and password for `<username>` and `<password>`.

