#!/bin/bash
echo "Initializing installer ..."

INSTALL_PATH=$1

echo "Done initializing installer .."

echo "Searching existing directory location in file path " $1


if [ -d $INSTALL_PATH ];
then
    echo "An existing installation found"
    echo "Continue installation will discard the existing installation"
    while true; do
        read -p "Do you want to continue? [y/n]" yn
        case $yn in
            Y | y | yes | Yes | YES ) echo -n "Discarding existing installation ... "
                                if rm -rf ${INSTALL_PATH}; then
                                    echo "Done"
                                    echo -n "Installing... "
                                    mkdir -vp ${INSTALL_PATH}
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
    mkdir -vp ${INSTALL_PATH}
fi

if [ $ABORT ];
then
    echo "Deployment procedure aborted te "
else
    echo -n "Installing hermese ... "
    if tar -xvf hermese.tar -C ${INSTALL_PATH}; then
        echo "Done"
        echo "Installation completed"
    else
        echo "Error"
        echo "Installation aborted abnormally"
        echo "Are you root ?"
    fi
fi