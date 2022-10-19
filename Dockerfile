---
- hosts: webservers
  become: true
  tasks: 
  - name: install docker and python pip
    yum: 
      name: "{{ item }}"
      state: present
    loop:
        - docker
        - python-pip
      tags:
          - install
  - name: install docker.py
    pip:
      name: docker.py
      state: present
      tags: 
          - install
  - name: start and enable docker
    service:
      name: docker
      state: started
      enabled: true
      tags: 
        - start
        - enable
  - name: run containers
    docker_container:
      name: myapp
      image: kammana/nodeapp:v1
      state: started
      ports:
        - 9090:8080
      tags:
        - deploy
