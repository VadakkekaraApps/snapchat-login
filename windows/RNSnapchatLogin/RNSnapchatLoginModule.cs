using ReactNative.Bridge;
using System;
using System.Collections.Generic;
using Windows.ApplicationModel.Core;
using Windows.UI.Core;

namespace Snapchat.Login.RNSnapchatLogin
{
    /// <summary>
    /// A module that allows JS to share data.
    /// </summary>
    class RNSnapchatLoginModule : NativeModuleBase
    {
        /// <summary>
        /// Instantiates the <see cref="RNSnapchatLoginModule"/>.
        /// </summary>
        internal RNSnapchatLoginModule()
        {

        }

        /// <summary>
        /// The name of the native module.
        /// </summary>
        public override string Name
        {
            get
            {
                return "RNSnapchatLogin";
            }
        }
    }
}
