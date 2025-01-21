FROM --platform=linux fedora:latest

ARG USERNAME=morphin
ARG USER_ID=1000
ARG USER_GID=${USER_ID}

# Parameters for Qt installation (needs to be the same as QtJambi's)
# Alter QT_VERSION to install different version of Qt
ARG QT_VERSION=6.8.1
ARG QT_INSTALL_DIR=/opt/Qt

ENV LD_LIBRARY_PATH=/opt/Qt/${QT_VERSION}/gcc_64/lib

# Setup user and enable passwordless sudo
RUN groupadd --gid ${USER_GID} ${USERNAME} && \
    useradd --uid ${USER_ID} --gid ${USER_GID} -m ${USERNAME} && \
    echo ${USERNAME} ALL=\(root\) NOPASSWD:ALL > /etc/sudoers.d/${USERNAME} && \
    chmod 0440 /etc/sudoers.d/${USERNAME}

# Update and install common tools
RUN dnf -y upgrade --refresh && \
    dnf -y install git zsh curl wget vim openssh

# Fix errors for developers attempting to use Qt xcb (X11 session)
RUN dnf -y install "xcb-util-*" libxkbcommon-x11

# Install Java OpenJDK 21 and Maven
RUN dnf -y install java-21-openjdk java-21-openjdk-src maven

# Install Python3 and gcc for PyQt6
RUN dnf -y install python3 python3-pip python3-devel python-pip-wheel gcc

# Install Another Qt installer (aqt)
RUN pip install -U pip && \
    pip install aqtinstall

# Install Qt in default install location QtJambi will look in
RUN aqt install-qt --outputdir ${QT_INSTALL_DIR} linux desktop ${QT_VERSION} linux_gcc_64

# Uninstall Another Qt installer (aqt)
RUN pip uninstall -y aqtinstall

USER ${USERNAME}

# RUN pip install PyQt6