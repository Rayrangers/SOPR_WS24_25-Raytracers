FROM fedora:latest

ARG USERNAME=morphin
ARG USER_ID=1000
ARG USER_GID=${USER_ID}

# Setup user and enable passwordless sudo
RUN groupadd --gid ${USER_GID} ${USERNAME} && \
    useradd --uid ${USER_ID} --gid ${USER_GID} -m ${USERNAME} && \
    echo ${USERNAME} ALL=\(root\) NOPASSWD:ALL > /etc/sudoers.d/${USERNAME} && \
    chmod 0440 /etc/sudoers.d/${USERNAME}

# Update and install common tools
RUN dnf -y upgrade --refresh && \
    dnf -y install git zsh curl wget vim openssh

# Install Java OpenJDK 21 and Maven
RUN dnf -y install java-21-openjdk java-21-openjdk-src maven

# Install Python3 and gcc for PyQt6
RUN dnf -y install python3 python3-pip python3-devel python-pip-wheel gcc

USER ${USERNAME}

RUN pip install PyQt6