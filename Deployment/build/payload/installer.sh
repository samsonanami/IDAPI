#!/bin/bash
echo "Initializing installer ..."

INSTALL_PATH="/opt/orion/hermese"

echo "Done initializing installer .."

echo -n "Searching existing directory location"

if [ -d $INSTALL_PATH ];
then
    echo "An existing installation found"
    echo "Continue installation will discard the existing installation"
    while true; do
        read -p "Do you want to continue? [y/n]" yn
        case $yn in
            Y | y | yes | Yes | YES ) echo -n "Discarding existing installation ... "
                                if rm -rf /opt/orion/hermese; then
                                    echo "Done"
                                    echo -n "Installing... "
                                    mkdir -vp /opt/orion/hermese
                                else
                                    echo "Unable to discard existing directory"
                                    echo "Are you root ?"
                                    ABORT=true;
                                fi
                                break ;;
            N | n | no | No | NO ) echo -n "Aborting installation procedure"
                                ABORT=true;
                                break ;;
        esac
    done
else
    echo "No existing installation found"
    echo "Proceeding with new installation"
    echo "Creating directory structure"
    mkdir -vp /opt/orion/hermese
fi

if [ $ABORT ];
then
    echo "Deployment procedure aborted te "
else
    echo -n "Installing hermese ... "
    if tar -xvf hermese.tar -C /opt/orion/hermese; then
        echo "Done"
        echo "Installation completed"
    else
        echo "Error"
        echo "Installation aborted abnormally"
        echo "Are you root ?"
    fi
fi